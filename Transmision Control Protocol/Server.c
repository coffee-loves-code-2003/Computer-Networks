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
    char string[20];
    recv(accept_client,string,sizeof(string),0);
    char string1[20];
    int i1,j1;
    int flag=0;
    int index=0;
    for(int i=0;i<strlen(string);i++)
    {
        for(int j=i;j<strlen(string);j++)
        {
            string1[index]=string[j];
            string1[index+1]='\0';
            index+=1;

            if(strlen(string1)==1)
            {
                continue;
            }
            else
            {
                for(i1=0,j1=strlen(string1)-1;i1<strlen(string1),j1>=0;i1++,j1--)
                {
                    if(string1[i1]==string1[j1])
                    {
                        continue;
                    }
                    else
                    {
                        ++flag;
                        break;
                    }
                }
                if(flag==0)
                {

                    strcat(substrings,string1);
		    strcat(substrings,"\n");
		    
                }
                flag=0;
            }
        }
        index=0;
    }
    send(accept_client,substrings,strlen(substrings),0);
}
