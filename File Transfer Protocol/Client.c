#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/types.h>
#include<arpa/inet.h>
#include<string.h>
int main()
{
	FILE *fp;
	fp=fopen("file1.txt","r");
	char array[200];
	char ch;
	int in=0;
	while((ch=fgetc(fp))!=EOF)
	{
	array[in]=ch;
	++in;
	}
	printf("%s\n",array);
	fclose(fp);
	
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
	char name[30];
	char password[30];
    int sizeof_server=sizeof(client);
    if(connect(client_socket_id,(struct sockaddr*)&client,sizeof(client))<0)
    {
        printf("Connection failed\n");
        exit(0);
    }
    printf("Enter the user name and password:\n");
    scanf("%s %s",name,password);
    send(client_socket_id,name,1024,0);
    send(client_socket_id,password,1024,0);
    send(client_socket_id,array,sizeof(array),0);
}

