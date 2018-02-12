#include <stdio.h>

int sum(int, int);

int sum(int a, int b){
    return a+b;
}

int main(int argc, char** argv){

    int x = 10, y = 20;

    int z = sum(x,y);
    printf("sum is: %d\n", z);

    return 0;
}