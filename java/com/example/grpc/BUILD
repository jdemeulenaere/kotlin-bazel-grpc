load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")
load("@io_grpc_grpc_java//:java_grpc_library.bzl", "java_grpc_library")

proto_library(
    name = "hello_proto",
    srcs = ["hello.proto"],
)

java_proto_library(
    name = "hello_java_proto",
    deps = [":hello_proto"]
)

java_grpc_library(
    name = "hello_java_grpc",
    srcs = [":hello_proto"],
    deps = [":hello_java_proto"],
)

kt_jvm_library(
    name = "server_lib",
    srcs = ["Server.kt"],
    deps = [":hello_java_grpc"],
    runtime_deps = ["@io_grpc_grpc_java//netty"],
)

java_binary(
    name = "server",
    main_class = "com.example.grpc.ServerKt",
    runtime_deps = [":server_lib"]
)

kt_jvm_library(
    name = "client_lib",
    srcs = ["Client.kt"],
    deps = [":hello_java_grpc"],
    runtime_deps = ["@io_grpc_grpc_java//netty"],
)

java_binary(
    name = "client",
    main_class = "com.example.grpc.ClientKt",
    runtime_deps = [":client_lib"]
)