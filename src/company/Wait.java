package company;

public class Wait {

    public static void waiting(int time){
        try{
            Thread.sleep(time);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
