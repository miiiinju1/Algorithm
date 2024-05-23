#include <iostream>
#include <vector>
#include <queue>
#include <map>
#include <climits>

using namespace std;

struct Node {
    int next, value, doubleValue, halfValue;

    Node(int next, int value) {
        this->next = next;
        this->value = value;
        this->doubleValue = value << 1;
        this->halfValue = value >> 1;
    }
};

struct Wolf {
    int now, value, turn;

    Wolf(int now, int value, int turn) {
        this->now = now;
        this->value = value;
        this->turn = turn;
    }

    bool operator<(const Wolf& other) const {
        return value > other.value; // for priority queue to work as min-heap
    }
};

struct Fox {
    int now, value;

    Fox(int now, int value) {
        this->now = now;
        this->value = value;
    }

    bool operator<(const Fox& other) const {
        return value > other.value; // for priority queue to work as min-heap
    }
};

map<int, vector<Node>> graph;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M;
    cin >> N >> M;

    for (int i = 1; i <= N; ++i) {
        graph[i] = vector<Node>();
    }

    for (int i = 0; i < M; ++i) {
        int a, b, d;
        cin >> a >> b >> d;
        d <<= 1;
        graph[a].emplace_back(b, d);
        graph[b].emplace_back(a, d);
    }

    vector<int> fox(N + 1, INT_MAX);
    vector<vector<int>> wolf(2, vector<int>(N + 1, INT_MAX));
    fox[1] = 0;
   // wolf[0][1] = 0;

    priority_queue<Fox> foxPq;
    priority_queue<Wolf> wolfPq;

    vector<int> visitedFox(N + 1, 0);
    vector<vector<int>> visitedWolf(2, vector<int>(N + 1, 0));

    foxPq.emplace(1, 0);
    wolfPq.emplace(1, 0, 0);

    while (!foxPq.empty()) {
        Fox now = foxPq.top();
        foxPq.pop();

        if (visitedFox[now.now]) continue;
        visitedFox[now.now] = 1;

        for (const Node& node : graph[now.now]) {
            int newCost = now.value + node.value;
            if (fox[node.next] > newCost) {
                fox[node.next] = newCost;
                foxPq.emplace(node.next, newCost);
            }
        }
    }

    while (!wolfPq.empty()) {
        Wolf now = wolfPq.top();
        wolfPq.pop();

        if (visitedWolf[now.turn][now.now]) continue;
        visitedWolf[now.turn][now.now] = 1;

        if (now.turn % 2 == 0) { // 2배 속도
            for (const Node& node : graph[now.now]) {
                int newCost = now.value + node.halfValue;
                if (wolf[0][node.next] > newCost) {
                    wolf[0][node.next] = newCost;
                    wolfPq.emplace(node.next, newCost, 1);
                }
            }
        } else { // 반 속도
            for (const Node& node : graph[now.now]) {
                int newCost = now.value + node.doubleValue;
                if (wolf[1][node.next] > newCost) {
                    wolf[1][node.next] = newCost;
                    wolfPq.emplace(node.next, newCost, 0);
                }
            }
        }
    }

    int sum = 0;
    for (int i = 2; i <= N; ++i) {
        if (fox[i] < min(wolf[0][i], wolf[1][i])) {
            ++sum;
        }
    }
    cout << sum << '\n';

    return 0;
}
