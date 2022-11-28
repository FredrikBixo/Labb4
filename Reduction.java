import java.util.*;

class Reduction  {

Kattio io;

Reduction() {
  io = new Kattio(System.in, System.out);
  readAndConvert();
}

public static void main(String args[])  {
  Reduction r = new Reduction();
}

public void readAndConvert() {
       int V = io.getInt();
       int no_roles = V+2; // Add two new roles for the divas.
       int E = io.getInt();
       int no_scenes = E+V+1; // Add a new scene for every role and 1 extra for diva2's role.
       int k = io.getInt(); // Max number of colors allowed to use.
       int m = Math.min(no_roles,k+2); //Max number of actors used. Cannot be higher than the number of roles (i.e when each role is played by a unique actor).
       // In the graph coloring problem, each node can only use k colors. In the role-assignment problem, each role can be played by a subset of m.
       // So in this sense, this particular aspect of the role-assignment problem is equivalent to graph coloring with restrictions.
       // So to reduce graph coloring indata to role assigment indata, we remove the restrictions, allowing each role to be played by k actors.

       ArrayList<Edge> scenes = new ArrayList<Edge>(no_scenes); //Represent scenes as edges

       // Create two new roles for the divas.
       int d1 = no_roles-1; // diva 1
       int d2 = no_roles; // diva 2

       // Connect each role to diva 1 (meaning they play in the same scene), in order to make sure that each role is playing in atleast 1 scene.
       // Note that adding these two divas will only add a constant value 2 to the max number of actors needed to solve the roll-assignment problem,
       // and that adding these two divas will not affect the assignment of actors, since the divas has an unique index that is not used in the rest of the problem.
       // The roles in the same scenes as the divas can therefore be played by any other actors than the divas.

       for (int i = 1; i <= V; i++) {
            Edge s = new Edge(i,d1);
            scenes.add(s);
       }

       // Connect diva 2 to role 1. This ensures that diva2 and diva1 are not in the same scene.
       Edge s = new Edge(1,d2);
       scenes.add(s);

       // Read all edges from the input and add to scenes array.
       for (int i = 0; i < E; i++) {
            int a = io.getInt();
            int b = io.getInt();

            Edge ab = new Edge(a,b);
            scenes.add(ab);
       }

      // ### PRINT OUTPUT ###
      System.out.println(no_roles); //Roles.
      System.out.println(no_scenes); //Scenes.
      System.out.println(m); //Max actors.

      // Skriv ut vilka skådespelare varje roll kan spelas av, dvs alla skådespelare utom divorna för varje roll.
       for (int i = 0; i < V; i++) {
           System.out.print(m-2 + " ");
           for (int j = 3; j <=m-1; j++) {
             System.out.print(j + " ");
           }
           System.out.println(m);
       }

       // Tilldela divorna varsin roll.
       System.out.println(1 + " " + 1);
       System.out.println(1 + " " + 2);

      // Print which roles are playing for each scene.
      for (Edge e : scenes) {
          System.out.println(2 + " " + (e.from) + " " + (e.to));
      }

}

}
