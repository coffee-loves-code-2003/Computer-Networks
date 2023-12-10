#include<stdio.h>
#include<string.h>
#include<arpa/inet.h>
#include<unistd.h>
#include<sys/types.h>
#include<stdlib.h>
void Find_Permutation(int arr[],int limit);
void compute_Permutation(int arr[],int low,int high,int limit);
int* swap(int arr[],int low,int high);
double low1=9999.9;
double num=0;
int i1=0;
int index1=0;
int limit;
int arr1[50];
int main()
{
    struct sockaddr_in server,client;
    server.sin_port=8086;
    server.sin_addr.s_addr=htons(INADDR_ANY);
    server.sin_family=AF_INET;
    int server_socket_id=socket(AF_INET,SOCK_DGRAM,0);
    if(server_socket_id<0)
    {
        printf("Error in creation of socket\n");
        exit(0);
    }
    if(bind(server_socket_id,(struct sockaddr*)&server,sizeof(server))<0)
    {
        printf("Error in binding the socket\n");
        exit(0);
    }
    int addr_len=sizeof(client);
    int limit;
    recvfrom(server_socket_id,&limit,sizeof(limit),0,(struct sockaddr*)&client,&addr_len);
    int array[limit];
    recvfrom(server_socket_id,array,sizeof(array),0,(struct sockaddr*)&client,&addr_len);
    Find_Permutation(array,limit);
    sendto(server_socket_id,arr1,sizeof(arr1),0,(struct sockaddr*)&client,sizeof(client));
    for(int i=0;i<limit;i++)
    {
                printf("%d\n",arr1[i]);

    }
    close(server_socket_id);
    
}
void Find_Permutation(int arr[],int limit)
{
    int high=limit;
compute_Permutation(arr,0,high-1,limit);

}
void compute_Permutation(int arr[],int low,int high,int limit)
{

        if(low==high)
        {
            while(i1<limit-2)
            {
                num+=(arr[index1]+arr[index1+1]+arr[index1+2])/3.0;


                ++index1;
                ++i1;
            }
            i1=0;
            index1=0;
            
            if(num<low1)
            {
                low1=num;
                for(int i=0;i<limit;i++)
                {
                    arr1[i]=arr[i];
                }
            }
            num=0;
        }
        for(int i=low;i<=high;i++)
        {
            arr=swap(arr,low,i);
            compute_Permutation(arr,low+1,high,limit);
            arr=swap(arr,low,i);
        }
        
}
int* swap(int arr[],int low,int high)
{
    int *a=arr;
    int temp;
   
    temp=a[low];
    a[low]=a[high];
    a[high]=temp;
    
    return a;

}