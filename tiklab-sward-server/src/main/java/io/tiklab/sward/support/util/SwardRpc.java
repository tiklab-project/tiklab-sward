package io.tiklab.sward.support.util;

import io.tiklab.rpc.client.RpcClient;
import io.tiklab.rpc.client.config.RpcClientConfig;
import io.tiklab.rpc.client.router.lookup.FixedLookup;

public class SwardRpc {

    public static SwardRpc instance (){
        return new SwardRpc();
    };

    public <T> T findServiceRpc(Class<T>  tClass,String serviceAddress){

        RpcClientConfig rpcClientConfig = RpcClientConfig.instance();
                // .setProtocol(RpcConstants.PROTOCOL_DRPC);
        RpcClient rpcClient = new RpcClient(rpcClientConfig);
        return rpcClient.getBean(tClass,new FixedLookup(serviceAddress));
    }


}
