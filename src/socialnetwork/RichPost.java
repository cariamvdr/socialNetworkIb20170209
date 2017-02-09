package socialnetwork;

/**
 *
 * @author riccobene
 */
public class RichPost extends Post {
    String link;
    
    RichPost(User author, String title, String body, String link) {
        super(author, title, body);
        this.link = link;
    }
    
    public void show() {
        super.show();
        System.out.println(link);
    }
}
