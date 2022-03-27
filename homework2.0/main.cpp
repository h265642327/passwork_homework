#include<bits/stdc++.h>
using namespace std;
class CStudent
{
private:
	char name[9];
	//string name;
	int num;
	double score;
public:
	CStudent();

	CStudent(const CStudent& a);
	~CStudent();
	void show();
};
CStudent::CStudent() {
	strcpy(name, "ads");
	//name = "dada";
	num = 12;
	score = 100.00;
}
CStudent::CStudent(const CStudent& a) {
	//strcpy(name, a.name);
	//name = "asdas";
	num = a.num;
	score = a.score;
}
CStudent::~CStudent() {}
inline void CStudent::show() {
	printf("%d,%.2f,%s", num, score, name);
}
int main()
{
	CStudent a;
	a.show();
	return 0;
}