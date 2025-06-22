package com.nhnacademy.heukbaekbook_rabbitmq.rabbitmq.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RabbitMqConfigResponse (
        String host,
        int port,
        @JsonProperty("username")
        String userName,
        String password,
        @JsonProperty("virtual-host")
        String virtualHost
){
}
