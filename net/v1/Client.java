package com.martinmimiGames.util.net.v1;

import java.net.Socket;

public interface Client {

  void onHostUnknown();

  void onConnectionFailed();

  void onConnect(Socket socket);

  void onClientClose();

}
