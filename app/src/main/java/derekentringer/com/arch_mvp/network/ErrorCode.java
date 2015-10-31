package derekentringer.com.arch_mvp.network;

import retrofit.HttpException;

public class ErrorCode {

    public static boolean is404(Throwable error) {
        return error instanceof HttpException && ((HttpException) error).code() == 404;
    }

}