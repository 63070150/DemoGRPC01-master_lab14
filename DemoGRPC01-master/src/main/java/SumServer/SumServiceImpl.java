package SumServer;

import com.proto.sum.*;
import io.grpc.stub.StreamObserver;

public class SumServiceImpl extends SumServiceGrpc.SumServiceImplBase {
    @Override
    public void sum(SumRequest request, StreamObserver<SumResponse> responseObserver) {
        // Block 1: extract the data required
        Sum sum = request.getSum();
        Integer num1 = sum.getNum1();
        Integer num2 = sum.getNum2();

        // Block 2: create the response message
        Integer result = (num1+num2);
        SumResponse response = SumResponse.newBuilder()
                .setResult(String.valueOf(result))
                .build();
        System.out.println("Server output: "+num1+" + "+num2+" = "+result);

        // Block 3: send the response
        responseObserver.onNext(response);

        // Block 4: complete the RPC call
        responseObserver.onCompleted();
    }
}
