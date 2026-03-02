package com.Challenge.Service;


public interface IConvierteDatos {
    // Este método es genérico (<T>): puede convertir el JSON a cualquier clase que le pidas
    <T> T obtenerDatos(String json, Class<T> clase);
}