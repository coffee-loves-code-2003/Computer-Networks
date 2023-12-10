#include<stdio.h>
#include<string.h>
#include<arpa/inet.h>
#include<unistd.h>
#include<sys/types.h>
#include<stdlib.h>
int main()
{
    struct sockaddr_in server;
    server.sin_port=8086;
    server.sin_addr.s_addr=htons(INADDR_ANY);
    server.sin_family=AF_INET;
    int server_socket_id=socket(AF_INET,SOCK_DGRAM,0);
    if(server_socket_id<0)
    {
        printf("Error in creation of socket\n");
        exit(0);
    }
    int limit;
    printf("Enter the limit of the array\n");
    scanf("%d",&limit);
    int array[limit];
    int index=0;
    for(int i=1;i<=limit;i++)
    {
        array[index]=i;
        ++index;
    }
    int addr_len=sizeof(server);
    sendto(server_socket_id,&limit,sizeof(limit),0,(struct sockaddr*)&server,sizeof(server));
    sendto(server_socket_id,array,sizeof(array),0,(struct sockaddr*)&server,sizeof(server));
    int array1[limit];
    recvfrom(server_socket_id,array1,sizeof(array1),0,(struct sockaddr*)&server,&addr_len);
    printf("OUTPUT\n");
    for(int i=0;i<limit;i++)
    {
        printf("%d",array1[i]);
    }
    close(server_socket_id);

}