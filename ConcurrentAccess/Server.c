#include<stdio.h>
#include<unistd.h>
#include<sys/types.h>
#include<stdlib.h>
#include<arpa/inet.h>
#include<string.h>
int final_arr1[30];
int final_arr2[30];
int final1=0;
int final2=0;
int main()
{
    struct sockaddr_in server,client;
    server.sin_family=AF_INET;
    server.sin_port=htons(8000);
    server.sin_addr.s_addr=htons(INADDR_ANY);
    int server_socket_id=socket(AF_INET,SOCK_STREAM,0);
    
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
    if(listen(server_socket_id,2)<0)
    {
        printf("Listening failed\n");
        exit(0);
    }
    int sizeof_server=sizeof(client);
    int i=1;
                int accept_client_3=accept(server_socket_id,(struct sockaddr*)&client,&sizeof_server);

        int pid=fork();
        if(pid==0)
        {
            printf("Inside Child Process:\n");
        int accept_client=accept(server_socket_id,(struct sockaddr*)&client,&sizeof_server);
        if(accept_client<0)
        {
            printf("Accepting client failed\n");
            exit(0);
        }
        
        int limit;
        recv(accept_client,&limit,sizeof(limit),0);
        int number[limit];
        recv(accept_client,number,sizeof(number),0);
        int numbers[limit];
        int index=0;
        int flag=0;
        for(int i=0;i<limit;i++)
        {
            if(number[i]==2)
            {
                numbers[index]=number[i];
                ++index;

                continue;
            }
            for(int j=2;j<number[i];j++)
            {
                if(number[i]%j==0)
                {
                    flag=1;
                    break;
                }
                continue;
            }
            if(flag==0)
            {
                numbers[index]=number[i];
                    ++index;


            }
            flag=0;
        }
        for(int i=0;i<index;i++)
        {
        final_arr1[final1]=numbers[i];
        ++final1;
        }
        send(accept_client_3,&index,sizeof(index),0);
        send(accept_client_3,numbers,sizeof(numbers),0);

        }
        else if(pid>0)
        {
            
            printf("Inside Parent Process:\n");

            int accept_client=accept(server_socket_id,(struct sockaddr*)&client,&sizeof_server);
        if(accept_client<0)
        {
            printf("Accepting client failed\n");
            exit(0);
        }
        
        int limit;
        recv(accept_client,&limit,sizeof(limit),0);
        int number[limit];
        recv(accept_client,number,sizeof(number),0);
        int numbers[limit];
        int index=0;
        int flag=0;
        for(int i=0;i<limit;i++)
        {
            if(number[i]==2)
            {
                numbers[index]=number[i];
                ++index;

                continue;
            }
            for(int j=2;j<number[i];j++)
            {
                if(number[i]%j==0)
                {
                    flag=1;
                    break;
                }
                continue;
            }
            if(flag==0)
            {
                numbers[index]=number[i];
                    ++index;


            }
            flag=0;
        }
        for(int i=0;i<index;i++)
        {
        final_arr2[final2]=numbers[i];
        ++final2;

        }
        send(accept_client_3,&index,sizeof(index),0);
        send(accept_client_3,numbers,sizeof(numbers),0);
        }
        

        close(server_socket_id);

}
