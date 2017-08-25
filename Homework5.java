/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.*;
import java.util.*;

class Homework5{
public static void main (String[] args){
List<Vertex> nodes = new ArrayList<Vertex>();
List<Edge> edges = new ArrayList<Edge>();
Scanner keyboard = new Scanner(System.in);
String pathOfFile= args[0], inputData, source = null, destination = null;
int totalLines, cost = 0, minutes = 0; 
int vertexIdCount=0;
int edgeIdCount=0;
int savedSource=0;
int savedDestination=0;
boolean failNode1=false,failNode2=false;
System.out.println("Enter path of document");
//pathOfFile = keyboard.nextLine();
File file = new File(pathOfFile);

try{
    Scanner fileScan = new Scanner(file);
    totalLines = Integer.parseInt(fileScan.nextLine()); 
    while(fileScan.hasNext())
    {
        inputData = fileScan.nextLine();
        System.out.println(inputData);
        String[] tokens = inputData.split("\\|");
        System.out.println("Data Extracted: " + tokens[0] + " " + tokens[1] + " " + tokens[2] + " " + tokens[3]);
        for(int x = 0; x < tokens.length; x++)
        {
            //just to check that the file is correct, we will assign the information to a variable for creation
            if(Character.isLetter(tokens[x].charAt(0))&& x==0)
                source = tokens[x];
            if(Character.isLetter(tokens[x].charAt(0))&& x==1)
                destination = tokens[x];
            if(Character.isDigit(tokens[x].charAt(0))&& x==2)
                cost = Integer.parseInt(tokens[x]);
            if(Character.isDigit(tokens[x].charAt(0))&& x==3)
                minutes = Integer.parseInt(tokens[x]);
        }
        //if vertex does not exist create it
        if(nodes.size()==0)
                {
                 nodes.add(new Vertex(String.valueOf(vertexIdCount), source));
                 System.out.println("New basic node: " + source);
               vertexIdCount++;
                 nodes.add(new Vertex(String.valueOf(vertexIdCount), destination));
                 System.out.println("New basic node: " + destination);
               vertexIdCount++;
                }
        else{
       for(int n = 0; n<nodes.size();n++)
       {
           if(nodes.get(n).getName().equals(source))
           {
               System.out.println("node1 failed the unique test");
               failNode1=true;
           }
        
           if(nodes.get(n).getName().equals(destination))
           {
               System.out.println("node2 failed the unique test");
               failNode2=true;
           }
         
      
    }
       if(!failNode1)
       {
            nodes.add(new Vertex(String.valueOf(vertexIdCount), source));
               System.out.println("New node: " + source);
               vertexIdCount++;
       }
       if(!failNode2)
       {
            nodes.add(new Vertex(String.valueOf(vertexIdCount), destination));
               System.out.println("New node: " + destination);
               vertexIdCount++;
       }
       //reset the booleans to catch on next datainput
       if(failNode1||failNode2)
       {
           failNode1=false;
           failNode2=false;
       }
        }
        //create the edge
        //problem encountered: how do I acces the vertexe object I just created reliably so that I can create the edge
        if(nodes.size()==0)
            System.out.println("No new nodes were created");
        
        for(int y = 0; y < nodes.size(); y++)
        {
            if(nodes.get(y).getName().equals(source))
                savedSource = y;
            if(nodes.get(y).getName().equals(destination))
                savedDestination = y;
        }
        edges.add(new Edge(String.valueOf(edgeIdCount),nodes.get(savedSource),nodes.get(savedDestination),cost,minutes));
        edges.add(new Edge(String.valueOf(edgeIdCount),nodes.get(savedDestination),nodes.get(savedSource),cost,minutes));

        edgeIdCount++;
        
    }
}
catch(Exception e)
{
    System.out.println(e);
}
/*
for(int c = 0; c<nodes.size(); c++)
{
    System.out.println(nodes.get(c).getName());
}
for(int q = 0; q<edges.size(); q++)
{
    System.out.println(edges.get(q).toString());
}
*/
//flight plans to be solved
String solvableFile = args[1];
String writeSolvableTo = args[2];
File solvableFlightPlan = new File(solvableFile);
String sourceSolvableFlight=null;
String destinationSolvableFlight=null;
String parameterSearchingFor = null;
Vertex sourceVertex = null;
Vertex destinationVertex = null;

try{
Scanner solvableFlights = new Scanner(solvableFlightPlan);

while(solvableFlights.hasNext())
{
inputData = solvableFlights.nextLine();
        System.out.println(inputData);
        String[] tokens = inputData.split("\\|");
        System.out.println("Data Extracted: " + tokens[0] + " " + tokens[1] + " " + tokens[2]);
        
        for(int x = 0; x < tokens.length; x++)
        {
            //just to check that the file is correct, we will assign the information to a variable for creation
            if(Character.isLetter(tokens[x].charAt(0))&& x==0)
                sourceSolvableFlight = tokens[x];
            if(Character.isLetter(tokens[x].charAt(0))&& x==1)
                destinationSolvableFlight = tokens[x];
            if(Character.isLetter(tokens[x].charAt(0))&& x==2)
                parameterSearchingFor = tokens[x];
        
        }

for(int counter = 0; counter < nodes.size(); counter++)
{
    if(nodes.get(counter).getName().equals(sourceSolvableFlight))
    {
        sourceVertex = nodes.get(counter);
    }
    if(nodes.get(counter).getName().equals(destinationSolvableFlight))
    {
        destinationVertex = nodes.get(counter);
    }
}

Graph graph = new Graph(nodes, edges);
Dijkstra d = new Dijkstra(graph);
d.execute(sourceVertex);
LinkedList<Vertex> path = d.getPath(destinationVertex);


//store the following into a new file
File solved = new File(writeSolvableTo);
FileWriter writer = new FileWriter(solved, true);
PrintWriter pWriter = new PrintWriter(writer);

if(parameterSearchingFor.equals("T")||parameterSearchingFor.equals("t"))
{
for(int reit = 0; reit < path.size(); reit++)
{
    if(reit == path.size() -1)
    {
        pWriter.append(path.get(reit).getName());
    }
    else
    {
        pWriter.append(path.get(reit).getName()+"->");
    }
}

pWriter.println(" Time: " + d.minutes + ".\n");
System.out.println("Time operation checkpoint");

}
if(parameterSearchingFor.equals("C")||parameterSearchingFor.equals("c"))
{
for(int reit = 0; reit < path.size(); reit++)
{
    if(reit == path.size() -1)
    {
        pWriter.append(path.get(reit).getName());
    }
    else
    {
        pWriter.append(path.get(reit).getName()+"->");
    }
}
pWriter.println(" Cost: " + d.cost + ".\n");
System.out.println("Cost opertaion checkpoint");
}


pWriter.close();
}

}
catch(Exception e)
{
    System.out.println(e);
}


        //

}
}