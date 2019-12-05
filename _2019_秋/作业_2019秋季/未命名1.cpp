#define _CRT_SECURE_NO_WARNINGS 
#include <stdio.h>
#include <stdlib.h>
int majorityElement(int* nums, int numsSize) {
	int count = 1;
	int zhong = nums[0];
	for (int i = 1; i<numsSize; i++)
	{
		if (zhong == nums[i])
		{
			count++;
		}
		else
		{
			count--;
			if (count == 0)
			{
				zhong = nums[i + 1];
			}
		}
	}
	return zhong ;
}
int main()
{
	int num[] = {2,2,0,0,1,1,1,2,2};
	int len = sizeof(num) / sizeof(int);
	int a = majorityElement(num, len);
	printf("%d\n", a);
	system("pause");
	return 0;
}

