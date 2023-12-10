#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/types.h>
#include<arpa/inet.h>
int main()
{
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
    printf("Enter the size of the array:\n");
    scanf("%d",&limit);
    int array[limit];
    printf("Enter the elements:\n");
    for(int i=0;i<limit;i++)
    {
        scanf("%d",&array[i]);
    }
    send(client_socket_id,&limit,sizeof(limit),0);
    send(client_socket_id,array,sizeof(array),0);
    
    close(client_socket_id);
}
