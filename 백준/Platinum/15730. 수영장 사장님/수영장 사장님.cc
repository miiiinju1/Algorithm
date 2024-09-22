#include <iostream>
#include <vector>
#include <queue>
using namespace std;

struct Point {
    int y, x;
    Point(int y, int x) : y(y), x(x) {}
};

int dy[4] = {-1, 0, 1, 0};
int dx[4] = {0, -1, 0, 1};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M;
    cin >> N >> M;

    vector<vector<int>> map(N + 2, vector<int>(M + 2, 0));

    for (int i = 1; i <= N; ++i) {
        for (int j = 1; j <= M; ++j) {
            cin >> map[i][j];
        }
    }

    vector<vector<int>> ary(N + 2, vector<int>(M + 2, 0));

    int sum = 0;
    for (int h = 1; h <= 10000; ++h) {
        for (int i = 1; i < N + 1; ++i) {
            for (int j = 1; j < M + 1; ++j) {
                ary[i][j] = 0;
                if (map[i][j] < h) {
                    ary[i][j] = 1;
                }
            }
        }

        for (int i = 1; i < N + 1; ++i) {
            for (int j = 1; j < M + 1; ++j) {
                if (ary[i][j] == 1) {
                    int temp = 1;
                    queue<Point> q;
                    q.push(Point(i, j));
                    ary[i][j] = 0;

                    bool isBoundary = false;
                    while (!q.empty()) {
                        Point now = q.front();
                        q.pop();

                        for (int d = 0; d < 4; ++d) {
                            int Y = now.y + dy[d];
                            int X = now.x + dx[d];

                            if (Y >= 0 && X >= 0 && Y < N + 2 && X < M + 2) {
                                if (ary[Y][X] == 1) {
                                    ary[Y][X] = 0;
                                    ++temp;
                                    q.push(Point(Y, X));
                                } else {
                                    if (Y == 0 || X == 0 || Y == N + 1 || X == M + 1) {
                                        isBoundary = true;
                                    }
                                }
                            }
                        }
                    }

                    if (!isBoundary) {
                        sum += temp;
                    }
                }
            }
        }
    }

    cout << sum << "\n";

    return 0;
}
