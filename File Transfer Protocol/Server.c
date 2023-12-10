#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/types.h>
#include<arpa/inet.h>
int main()
{
    struct sockaddr_in server,client;
    server.sin_family=AF_INET;
    server.sin_port=htons(8788);
    server.sin_addr.s_addr=htons(INADDR_ANY);
    int server_socket_id=socket(AF_INET,SOCK_STREAM,0);
    char substrings[1024];
    if(server_socket_id<0)
    {
        printf("Socket creation failed\n");
        exit(0);
    }
    if(bind(server_socket_id,(struct sockaddr*)&server,sizeof(server))<0)
    {
        printf("Bind creation failed\n");
        exit(0);
    }
    if(listen(server_socket_id,3)<0)
    {
        printf("Listening failed\n");
        exit(0);
    }
    int sizeof_server=sizeof(client);
    int accept_client=accept(server_socket_id,(struct sockaddr*)&client,&sizeof_server);
    if(accept_client<0)
    {
        printf("Accepting client failed\n");
        exit(0);
    }
    char name[40];
    char password[40];
    recv(accept_client,name,1024,0);
    recv(accept_client,password,1024,0);
    if(strcmp(name,"Vignesh")!=0 || strcmp(password,"vicky2003")!=0)
    {       printf("%s\n",name);
	    printf("%s\n",password);
	    printf("Authentication Failed:\n");
	exit(0);
    }
    char array[200];
    FILE *fp;
    fp=fopen("File2.txt","w");
    int letter;
    while((letter=recv(accept_client,array,sizeof(array),0))>0)
    {
	    fwrite(array,1,letter,fp);
    }
   
   
    close(server_socket_id);
}
