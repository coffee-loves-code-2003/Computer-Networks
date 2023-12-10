#include<stdio.h>
#include<string.h>
#include<arpa/inet.h>
#include<unistd.h>
#include<sys/types.h>
int main()
{
    struct sockaddr_in server,client;
    int array[20];
    server.sin_port=8788;
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
    recvfrom(server_socket_id,array,sizeof(array),0,(struct sockaddr*)&client,&addr_len);
    for(int i=0;i<5;i++)
    {
        printf("%d\n",array[i]);
    }
    
}