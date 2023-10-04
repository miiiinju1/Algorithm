#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;

int main() {
   int money = 5000000; // 5 * 10^6
   int T, n; // T = testcase, ans = 답, n은 임시변수
   long long ans;
    vector<int> v;

   cin >> T; 

   for (int i = 0; i < T; i++) {
      ans = 0;

      while (true) {
         cin >> n;
         if (n == 0) // 0이 마지막이니 브레이크
            break;
         v.push_back(n); // 0이 아니면 벡터에 추가
      }

      //벡터를 오름차순 정렬 후 리버스시켜 내림차순으로 변경
      sort(v.begin(), v.end());
      reverse(v.begin(), v.end());

      for (int j = 0; j < v.size(); j++) 
         ans += (2 * pow(v[j], j + 1)); // 2*가격^년수(j가 0부터니 +1)

      if (ans > money) // 답이 머니보다 크면 비싸다
         cout << "Too expensive" << endl;
      else // 아니면 출력
         cout << ans << endl;

      v.clear(); //쓴 벡터 클리어
   }
}