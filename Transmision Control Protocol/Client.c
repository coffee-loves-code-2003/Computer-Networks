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
    client.sin_port=htons(8788);
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
    char string[20];
    printf("Enter the string:\n");
    scanf("%s",string);
    send(client_socket_id,string,strlen(string),0);
   
    char substrings[1024];
    recv(client_socket_id,substrings,sizeof(substrings),0);
    printf("%s\n",substrings);
}
