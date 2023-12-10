#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/types.h>
#include<arpa/inet.h>
int main()
{
    int array1[30];
    int array2[30];
    struct sockaddr_in client;
    client.sin_family=AF_INET;
    client.sin_port=htons(8000);
    client.sin_addr.s_addr=htons(INADDR_ANY);
    int client_socket_id=socket(AF_INET,SOCK_STREAM,0);
    if(client_socket_id<0)
    {
        printf("Socket creation failed\n");
        exit(0);
    }
    
    int sizeof_server=sizeof(client);
    if(connect(client_socket_id,(struct sockaddr*)&client,sizeof(client))<0)
    {
        printf("Connection failed\n");
        exit(0);
    }
    int limit;
    int limit2;
    recv(client_socket_id,&limit,sizeof(limit),0);
    recv(client_socket_id,array1,sizeof(array1),0);
    recv(client_socket_id,&limit2,sizeof(limit2),0);
    recv(client_socket_id,array2,sizeof(array2),0);

    printf("The prime numbers are:\n");
    printf("From Client 1:\n");
    for(int i=0;i<limit;i++)
    {
        printf("%d\n",array1[i]);
    }
    printf("From Client 2:\n");
    for(int i=0;i<limit2;i++)
    {
        printf("%d\n",array2[i]);
    }
    close(client_socket_id);
}
