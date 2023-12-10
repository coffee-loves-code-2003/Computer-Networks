#include<stdio.h>
#include<netdb.h>
#include<arpa/inet.h>
#include<string.h>
#include<unistd.h>
#include<stdlib.h>
int main()
{
        char name[40];
        int socket_id;
        struct hostent *host;
        
        
        socket_id=socket(AF_INET,SOCK_DGRAM,0);
        if(socket_id<0)
        {
                printf("Error in Socket Creation\n");
                exit(0);
        }
        struct sockaddr_in server,client;
        server.sin_family=AF_INET;
        server.sin_port=htons(8788);
        server.sin_addr.s_addr=htons(INADDR_ANY);
	if((bind(socket_id,(struct sockaddr*)&server,sizeof(server))<0))
	{
				printf("Binding failed:\n");
				exit(0);
	}
	char domain_name[100];
	int client_in=sizeof(client);
	recvfrom(socket_id,domain_name,sizeof(domain_name),0,(struct sockaddr*)&client,&client_in);
	printf("%s\n",domain_name);
	host=gethostbyname(domain_name);
        struct in_addr **addr_list=(struct in_addr**)host->h_addr_list;
        printf("Host-name:%s\n",host->h_name);
        for(int i=0;addr_list[i]!=NULL;i++)
        {
                printf("IP address %d: %s\n",i+1,inet_ntoa(*addr_list[i]));
        }
        return 0;
}
