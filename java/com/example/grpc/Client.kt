package com.example.grpc

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.StatusRuntimeException
import java.util.concurrent.TimeUnit

fun main() {
    val host = "127.0.0.1"
    val port = 50051

    val channel = ManagedChannelBuilder.forAddress(host, port)
        // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
        // needing certificates.
        .usePlaintext()
        .build()

    val stub = GreeterGrpc.newBlockingStub(channel)
    val name = "Foo Bar"
    val request = HelloRequest.newBuilder().setName(name).build();

    println("Sending request...")
    try {
        println("Greeting: " + stub.sayHello(request).message)
    } finally {
        println("Shutting down client...")
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        println("Client shut down.")
    }    
}