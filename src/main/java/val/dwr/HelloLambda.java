package val.dwr;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class HelloLambda implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(final Object input, final Context context) {
        System.out.println(input);
        return "Hello From Lambda";
    }
}
