#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>
#include <unistd.h>
#include <stdint.h>

int main(int argc, char **argv) {

	/*korzystam z szablonu z zajec */

	int sock_fd, cli_fd;
	socklen_t cli_len;
	struct sockaddr_in serv_addr;
	struct sockaddr_in cli_addr;
	int ret;

	if (argc != 2) {
		printf("usage: %s <TCP port>\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	sock_fd = socket(AF_INET, SOCK_STREAM, 0);
	if (!sock_fd) {
		perror("socket");
		exit(EXIT_FAILURE);
	}

	bzero(&serv_addr, sizeof(serv_addr));
	serv_addr.sin_family 		= AF_INET;
	serv_addr.sin_addr.s_addr 	= htonl(INADDR_ANY);
	serv_addr.sin_port 			= htons(atoi(argv[1]));

	// ustawiam SO_REUSEADDR dla wygody
	int so_reuseaddr = 1;
	ret = setsockopt(sock_fd,SOL_SOCKET,SO_REUSEADDR,&so_reuseaddr, sizeof so_reuseaddr);
	if (ret<0) {
		perror("setsockopt");
		close(sock_fd);
		exit(EXIT_FAILURE);
	}

	ret = bind(sock_fd, (struct sockaddr*)&serv_addr, sizeof(serv_addr));
	if (ret<0) {
		perror("bind");
		close(sock_fd);
		exit(EXIT_FAILURE);
	}

	ret = listen(sock_fd, 5);
	if (ret<0) {
		perror("listen");
		close(sock_fd);
		exit(EXIT_FAILURE);
	}

	while(1) {

		cli_fd = accept(sock_fd, (struct sockaddr*)&cli_addr, &cli_len);
		if(cli_fd < 0){
			perror("accept");
			close(sock_fd);
			exit(EXIT_FAILURE);
		}

		char odebrane[4];
		//---------------------------------------------------------------
		//-----------------------JEDEN BAJT------------------------------
		//---------------------------------------------------------------

		int len = recv(cli_fd, odebrane, 1, 0);
		int8_t liczba1 = *odebrane;
		printf("JEDEN BAJT\n");
		printf("liczba odebrana na serwerze: %d\n", (int) liczba1);
		liczba1++;
		len = send(cli_fd, &liczba1, 1, 0);
		if(len<0){
				perror("send");
				close(sock_fd);
				close(cli_fd);
				exit(EXIT_FAILURE);
		}
		printf("liczba wyslana od serwera:   %d\n\n", (int) liczba1);

		//---------------------------------------------------------------
		//-----------------------DWA BAJTY-------------------------------
		//---------------------------------------------------------------

		int n = 0;
		while (n < 2) {
			len =recv(cli_fd, odebrane+n, 2-n, 0);
			if(len<0){
				perror("recv");
				close(sock_fd);
				close(cli_fd);
				exit(EXIT_FAILURE);
			}
			n += len;
		}
		int16_t liczba2;
		memcpy(&liczba2, odebrane, 2);
		liczba2 = ntohs(liczba2);
		printf("DWA BAJTY\n");
		printf("liczba odebrana na serwerze: %d\n", (int)liczba2);
		liczba2++;
		printf("liczba wyslana od serwera:   %d\n\n", (int)liczba2);
		liczba2 = htons(liczba2);

		len = send(cli_fd, &liczba2, 2, 0);
		if(len<0){
			perror("send");
			close(sock_fd);
			close(cli_fd);
			exit(EXIT_FAILURE);
		}

		//---------------------------------------------------------------
		//-----------------------CZTERY BAJTY----------------------------
		//---------------------------------------------------------------
		n = 0;
		while (n < 4) {
			n +=recv(cli_fd, odebrane+n, 4-n, 0);
			if(len<0){
				perror("recv");
				close(sock_fd);
				close(cli_fd);
				exit(EXIT_FAILURE);
			}
		}
		int32_t liczba4;
		memcpy(&liczba4, odebrane, 4);
		liczba4 = ntohl(liczba4);
		printf("CZTERY BAJTY\n");
		printf("liczba odebrana na serwerze: %d\n", liczba4);
		liczba4++;
		printf("liczba wyslana od serwera:   %d\n\n", liczba4);
		liczba4 = htonl(liczba4);
		len = send(cli_fd, &liczba4, 4, 0);
		if(len<0){
			perror("send");
			close(sock_fd);
			close(cli_fd);
			exit(EXIT_FAILURE);
		}

		close(cli_fd);
	}

	return EXIT_SUCCESS;
}
