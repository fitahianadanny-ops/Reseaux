#include <iostream>
#include <cstring>
#include <string>
#include <winsock2.h>
#include <ws2tcpip.h>
#pragma comment(lib, "ws2_32.lib")

int main(int argc, char const *argv[])
{
    //1 . varification des arguments
 
    if (argc<3)
    {
        std::cerr<<"Usage : "<<argv[0]<<"<port> <taille_buffer>\n";
        return 1 ;
    }

    int port = std::stoi(argv[1]);
    int bufferSize = std::stoi(argv[2]);

    //1 . initialiser la bibliothèque winsock
    WSADATA wsaData;
    if (WSAStartup(MAKEWORD(2,2),&wsaData)!=0){
        std::cerr<<"Erreur : WSAStartup() a échoué. Code : "<<WSAGetLastError()<<"\n";
        return 1;
    }
    
    //2 . creer un socket UDP sans la lier (port local attribué auto)
    SOCKET sock = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);
    if(sock == INVALID_SOCKET){
        std::cerr<<"Erreur : socket() a échoué. Code "<<WSAGetLastError()<<"\n";
        WSACleanup();
        return 1;
    }

    //3. lier (bind) la socket au port
    sockaddr_in serverAddr;
    ZeroMemory(&serverAddr,sizeof(serverAdd));
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(port);
    serverAddr.sin_addr.s_addr = INADDR_ANY;   /* TOUT LES INTERFACES */

    if (bind(sock,(sockaddr*)&serverAddr, sizeof(serverAddr)) == SOCKET_ERROR)
    {
        std::cerr<<"Erreur : bind() a échoué sur le port "<<port<<". Code "<<WSAGetLastError()<<"\n";
        WSACleanup();
        return 1;
    }
    std::cout<<"Serveur UDP en écoute sur le port "<<port<<"...\n";

    //4 . boucle de repetition
    std::string buffer(bufferSize, '\0');
    bool done = false;
    while (!done)
    {
        sockaddr_in clientAddr;
        int clientLen = sizeof(clientAddr);
        ZeroMemory(&clientAddr, sizeof(clientAddr));
        
        // recvform() remplit client avec l'adress de l'emetteur
        int bytesReceived = recvform(
            sock,
            &buffer[0],
            bufferSize -1,
            0,
            (sockaddr*)&clientAddr,
            &clientLen
        );
        if (bytesReceived == SOCKET_ERROR)
        {
            std::cerr<<"Erreur : recevform() a échoué. Code : "<<WSAGetLastError()<<"\n";
            break;
        }
        buffer[bytesReceived] = '\0';
        //Convertir l'address IP binaire en chaine lisible
        char ipStr[INET_ADDRSTRLEN];
        inet_ntop(AF_INET, &clientAddr.sin_addr, ipStr, sizeof(ipStr));
        std::cout<<ipStr<<" au port "<< ntohs(clientAddr.sin_port)<<" : "<<buffer.substr(0,bytesReceived)<<"\n";

    }
    //5 . fermeture de la socket et netoyage winsok
    closesocket(sock);
    WSACleanup();   
    

    return 0;
}
