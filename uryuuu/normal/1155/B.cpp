#include<iostream>
#include<cstdio>
#include<vector>
#include<algorithm>
#include<cstring>
#include<cmath>
#include<queue>
#include<string>
#include<stack>
#include<set>
#include<map>
#define ll long long
int inf=0x3f3f3f3f;
using namespace std;
int main()
{
    string s;
    int n;
    cin>>n;
    cin>>s;
    int cnt=0;
    for(int i=0;i<n-10;i++)
    {
        if(s[i]=='8')
            cnt++;
        else cnt--;
    }
    if(cnt>0)
        cout<<"YES"<<endl;
    else
        cout<<"NO"<<endl;
    return 0;
}