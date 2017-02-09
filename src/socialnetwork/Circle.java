/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork;

import java.util.ArrayList;


public class Circle {
    protected String title;
    protected ArrayList<User> members = new ArrayList<User>();
    protected ArrayList<Post> posts = new ArrayList<Post>();
    
    public Circle(String title) {
        this.title = title;
    }
    
    public void add(User newMember) {
        members.add(newMember);
    }
    
    public void publish(Post newPost) throws Exception
    {
        if (! members.contains(newPost.author))
            throw new Exception("eh no bello mio!");
        
        posts.add(newPost);
    }
    
    public void showWall() {
        System.out.println("------ Circle " + title + " ------");
        for(int i=0; i < posts.size(); i++) {
            Post p = posts.get(i);

            p.show();
        }
        System.out.println("----------------------------------");    
    }   
}
