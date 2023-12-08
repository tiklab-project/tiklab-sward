package io.thoughtware.sward.support.util;

import io.thoughtware.rpc.client.RpcClient;
import io.thoughtware.rpc.client.config.RpcClientConfig;

public class RpcClientTeamWireUtil {
    public RpcClient rpcClient(){
        RpcClientConfig rpcClientConfig = RpcClientConfig.instance();
        RpcClient rpcClient = new RpcClient(rpcClientConfig);
        return rpcClient;
    }
}
