#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <strings.h>
#include <unistd.h>

#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/socket.h>
#include <netinet/in.h>

#define BUFLEN 16

int main(int argc, char **argv) {

	int sock_fd, cli_fd;
	int len;
	socklen_t cli_len;
	struct sockaddr_in serv_addr;
	struct sockaddr_in cli_addr;
	int ret;

	if (argc != 3) {
		printf("usage: %s <TCP port> <plik>\n", argv[0]);
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

	int so_reuseaddr = 1;
	ret = setsockopt(sock_fd,SOL_SOCKET,SO_REUSEADDR,&so_reuseaddr, sizeof so_reuseaddr);
	if (ret<0) {
		perror("setsockopt"); //zamknac
		exit(EXIT_FAILURE);
	}

	ret = bind(sock_fd, (struct sockaddr*)&serv_addr, sizeof(serv_addr));
	if (ret<0) {
		perror("bind");//zamknac
		exit(EXIT_FAILURE);
	}

	ret = listen(sock_fd, 5);
	if (ret<0) {
		perror("socket");//zamknac
		exit(EXIT_FAILURE);
	}

	while(1){

		//zaakceptuj polaczenie
		cli_fd = accept(sock_fd, (struct sockaddr*)&cli_addr, &cli_len);
		if (!cli_fd) {
			perror("accept");//zamknac
			exit(EXIT_FAILURE);
		}

		//otworz plik
		int plik = open(argv[2], O_RDONLY);
		if(plik<0){
			perror("open");
			exit(EXIT_FAILURE);
		}

		//wysylaj, dopoki nie wyslesz wszystkiego
		char bufor[BUFLEN];
		int ile=1;
		while(ile!=0){
			//odczytaj bajty z pliku
			ile = read(plik, &bufor, sizeof(bufor));
			if(ile<0){
				perror("read");
				close(plik);								//tu sie daje obsluge?
				close(sock_fd);
				exit(EXIT_FAILURE);
			}

			len = send(cli_fd, &bufor, ile, 0);
			if(len<0){
				perror("send");
				close(plik);								//to samo
				close(cli_fd);
				exit(EXIT_FAILURE);
			}
		}
		close(cli_fd);										//?
		close(plik);
		printf("plik zostal przeslany\n");

	}

	close(sock_fd);											//?
	return EXIT_SUCCESS;
}
