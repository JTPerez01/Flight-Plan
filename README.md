# Flight-Plan
Use Dijkstra's algorithm to find the nearest flight plan and then calculate cost.

This program will take in two files:

1. The file that hast the list of all edges to edge connections with associated costs.

2. The list of flight plans to be solved.

It will return one item:

1. The flight plans solved for either time, or cost.

The format of the input files will be like Austin|Chicago|T in which case it will solve Austin to Chicago for time. 
It can also be like Austin|Chicago|C which will solve for cost.

The input file will be in the form City1|City2|TimeToFly|CostToFly and will be constructed into a graph and stored into an array that will be processed in a Djikstra object.

//The Dijkstra object file modified form an existing algorithm found online.

The files will be outputed into a txt file using printwriter.
