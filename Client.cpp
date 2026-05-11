#include <iostream>
#include <cstring>
#include <string>
#include <winsock2.h>
#include <ws2tcpip.h>
#pragma comment(lib, "ws2_32.lib")

 int main(int argc, char const *argv[])
{
    //verification des argument
    if (argc < 3){
        std::cerr<<"Usage : "<<argv[0]<<" <adresse_serveur> <port>\n";
        return 1;
    }

    const char* serveurName = argv[1];
    int port = std::stoi(argv[2]);

    //1 . initialiser la bibliothèque winsock
    WSADATA wsaData;
    if (WSAStartup(MAKEWORD(2,2),&wsaData)!=0){
        std::cerr<<"Erreur : WSAStartup() a échoué. Code : "<<WSAGetLastError()<<"\n";
        return 1;
    }

    //2 . resoudre le nom d'hote en adress ip
    addrinfo hints, *res;
    ZeroMemory(&hints, sizeof(hints));
    hints.ai_family = FA_INET;
    hints.ai_socktype = SOCK_DGRAM;
    if (getaddrinfo(serverName, nullptr , &hints, &res)!=0)
    {
        std::cerr<<"Erreur : impossible de résoudre l'hôte "<<serverName << " . Code : "<<WSAGetLastError()<<"\n";
        WSACleanup();
        return 1;
    }
    //l'adresse de destinationavec le port fourni
    sockaddr_in serverAdd;
    ZeroMemory(&serverAddr,sizeof(serverAdd));
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(port);
    serverAddr.sin_addr = ((sockaddr_in*)res->ai_addr)->sin_addr;
    freeaddrinfo(res);

    //3 . creer un socket UDP sans la lier (port local attribué auto)
    SOCKET sock = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);
    if(sock == INVALID_SOCKET){
        std::cerr<<"Erreur : socket() a échoué. Code "<<WSAGetLastError()<<"\n";
        WSACleanup();
        return 1;
    }

    //4. boucle de la saise et d'envoi
    std::cout<<"begin typing(entre pour envoyer, Ctrl+C pour quitter) : \n";
    std:: string line;
    while (std::getline(std::cin, line))
    {
        /* envoyer le message au serveur
        equivalent JAVA: theSocket.send(the output) */
        int bytesSent = sendto(
            sock,
            line.c_str(),
            (int)line.size(),
            0,
            (sockaddr*)&serverAddr,
            sizeof(serverAddr)
        );
        if (bytesSent == SOCKET_ERROR)
        {
            std::cerr<<"Erreur : sendto() a échoué. Code: "<<WSAGetLastError()<<"\n";
            break;
        }
    }
    //5 . fermeture de la socket et netoyage winsok
    closesocket(sock);
    WSACleanup();   
    
    return 0;
}
