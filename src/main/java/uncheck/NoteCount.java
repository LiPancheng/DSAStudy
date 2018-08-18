package uncheck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;

/**
 * c语言有两种注释，单行注释和多行注释。请编写程序统计注释数量。
 * 注意引号中的不要统计，以及引号中可能出现的转义字符影响。
 *
 * TODO: 还未全部测试通过
 */
public class NoteCount {
    int count = 0;
    boolean hasQuotation = false;
    Stack<String> blockNote = new Stack<>();

    public static void main(String[] args) {
        NoteCount noteCount = new NoteCount();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("E:\\study\\tmp.txt"));
            String line;
            while ((line = bufferedReader.readLine())!= null){
                noteCount.lineHandle(line);
            }
        }catch (Exception e){

        }
        System.out.println(noteCount.count);
    }

    public void lineHandle(String line){
        String str = dropString(line);
        if (str.contains("//")){
            count++;
            return;
        }
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) == '/'){
                if(i+1 < len && str.charAt(i+1)=='*'){
                    blockNote.add("/*");
                }
            }
            if (str.charAt(i) == '*'){
                if(i+1 < len && str.charAt(i+1)=='/'){
                    count++;
                    blockNote.pop();
                }
            }
        }
    }

    public String dropString(String str){
        int squo = str.indexOf('\"');
        if (squo < 0)
            return str;
        if (squo > 1 && str.charAt(squo-1) != '\\'){
            if (hasQuotation){
                hasQuotation = false;
                return dropString(str.substring(squo+1, str.length() - 1));
            }
            else {
                hasQuotation = true;
                return dropString(str.substring(0, squo));
            }
        }
        else return str;
    }
}

