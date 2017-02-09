/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork;

public class Post {
    static long prevId = 0;
    protected long id;
    protected String title;
    protected String body;
    protected User author;
    
    public Post(User author, String title, String body) {
        this.title = title;
        this.body = body;
        this.author = author;
        prevId++;
        this.id = prevId;        
    }
    
    public void show() {
        author.showAvatar();
        System.out.println("- " + title + " -");
        System.out.println(body);
    }
}
