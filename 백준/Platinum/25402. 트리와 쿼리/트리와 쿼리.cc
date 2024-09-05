#include <iostream>
#include <vector>
#include <unordered_map>
#include <unordered_set>
#include <queue>
#include <algorithm>

using namespace std;

int N;
vector<int> parent;
vector<int> group_size;  // 'size'를 'group_size'로 변경

int find(int v) {
    if (parent[v] == v) {
        return v;
    }
    parent[v] = find(parent[v]);
    return parent[v];
}

void union_set(int a, int b) {
    int fa = find(a);
    int fb = find(b);

    if (fa != fb) {
        if (group_size[fa] < group_size[fb]) {  // 'size'를 'group_size'로 변경
            swap(fa, fb);
        }
        group_size[fa] += group_size[fb];  // 'size'를 'group_size'로 변경
        group_size[fb] = 0;  // 'size'를 'group_size'로 변경
        parent[fb] = fa;
    }
}

unordered_map<int, vector<int>> graph;
vector<int> treeParent;
vector<bool> visited;

void dfs(int start, int parent) {
    queue<int> q;
    q.push(start);
    visited[start] = true;
    treeParent[start] = parent;

    while (!q.empty()) {
        int now = q.front();
        q.pop();

        for (int neighbor : graph[now]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                treeParent[neighbor] = now;
                q.push(neighbor);
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> N;

    parent.resize(N + 1);
    treeParent.resize(N + 1);
    group_size.resize(N + 1);  // 'size'를 'group_size'로 변경
    visited.resize(N + 1, false);

    for (int i = 1; i <= N; i++) {
        graph[i] = vector<int>();
        parent[i] = i;
    }

    fill(group_size.begin(), group_size.end(), 1);  // 'size'를 'group_size'로 변경

    for (int i = 1; i < N; i++) {
        int a, b;
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }

    visited[1] = true;
    dfs(1, 0);

    int Q;
    cin >> Q;

    for (int i = 0; i < Q; i++) {
        int k;
        cin >> k;

        unordered_set<int> input;

        for (int j = 0; j < k; j++) {
            int num;
            cin >> num;
            input.insert(num);
        }

        for (int a : input) {
            int parA = treeParent[a];
            if (input.find(parA) != input.end()) {
                union_set(parA, a);
            }
        }

        unordered_set<int> visit;
        long long sum = 0;

        for (int num : input) {
            int numParent = find(num);
            int numSize = group_size[numParent];  // 'size'를 'group_size'로 변경
            if (numSize == 1) continue;
            long long size = (long long)numSize * (numSize - 1) / 2;

            if (visit.find(numParent) == visit.end()) {
                visit.insert(numParent);
                sum += size;
            }
        }

        cout << sum << "\n";

        for (int index : input) {
            parent[index] = index;
            group_size[index] = 1;  // 'size'를 'group_size'로 변경
        }
    }

    return 0;
}