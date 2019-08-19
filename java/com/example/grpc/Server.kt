package com.example.grpc

import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver

object GreeterImpl : GreeterGrpc.GreeterImplBase() {
    override fun sayHello(req: HelloRequest, response: StreamObserver<HelloReply>) {
        val reply = HelloReply.newBuilder()
            .setMessage("Hello ${req.name}")
            .build()
        response.onNext(reply)
        response.onCompleted()
    }
}

fun main() {
    val port = 50051

    println("Starting server...")
    val server = ServerBuilder.forPort(port)
        .addService(GreeterImpl)
        .build()
        .start()

    Runtime.getRuntime().addShutdownHook(object : Thread() {
        override fun run() {
            println("Shutting down server...");
            server.shutdown();
            println("Server shut down.");
        }
    });

    println("Server started, listening on $port.")
    server.awaitTermination()
}