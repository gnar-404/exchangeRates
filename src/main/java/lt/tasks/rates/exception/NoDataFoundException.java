package lt.tasks.rates.exception;

public class NoDataFoundException extends RuntimeException{
    public NoDataFoundException(){
        super("No data found");
    }
}
