#include<stdio.h>
#include<stdlib.h>
#include<netdb.h>
#include<arpa/inet.h>
#include<string.h>
int main()
{
	struct sockaddr_in server;
	server.sin_port=htons(8788);
	server.sin_family=AF_INET;
	server.sin_addr.s_addr=htons(INADDR_ANY);
	int socket_id;
	socket_id=socket(AF_INET,SOCK_DGRAM,0);
	if(socket_id<0)
	{
		printf("Socket creation failed:\n");
		exit(0);
	}
	char domain_name[100];
	printf("Enter the domain name:\n");
	scanf("%s",domain_name);
	sendto(socket_id,domain_name,sizeof(domain_name),0,(struct sockaddr*)&server,sizeof(server));
	close(socket_id);
}
