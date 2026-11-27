Detailed Problem Statement
Title: India State and City Population Directory System
Background:
Urban planning agencies and demographic researchers frequently need to store, update, and query population figures across different administrative levels. Maintaining flat lists makes hierarchical filtering slow and error-prone. A structured in-memory directory is needed to map Indian states to their major cities and respective population counts.
Objective:
Design and implement a Java application using the Java Collections Framework that stores a collection of Indian states, the major cities within each state, and the population of each city. The system must allow users or automated modules to dynamically add records, update population figures, and execute specific retrieval queries efficiently.



Title: India State and City Population Directory System


Background:
Urban planning agencies and demographic researchers frequently need to store, update, and query population figures across different administrative levels. Maintaining flat lists makes hierarchical filtering slow and error-prone. A structured in-memory directory is needed to map Indian states to their major cities and respective population counts.


Objective:
Design and implement a Java application using the Java Collections Framework that stores a collection of Indian states, the major cities within each state, and the population of each city. The system must allow users or automated modules to dynamically add records, update population figures, and execute specific retrieval queries efficiently.

Functional Requirements:
Hierarchical Data Storage: Store multiple states uniquely. Each state must map to a unique collection of its major cities, and each city must store its exact population count.
Data Insertion and Updates: Support adding new states, adding new cities to existing states, and updating the population of an existing city without duplicating state or city entries.
Query 1 (City Lookup): Given a state name and a city name, retrieve and display the exact population of that city.
Query 2 (State-Level Aggregation): Given a state name, retrieve all major cities in that state along with their individual populations and the combined total population of those cities.
Query 3 (Global Search): Given only a city name, find which state it belongs to and return its population.
Query 4 (Threshold Filtering): Retrieve a list of all cities across India whose population exceeds a user-defined threshold.
Edge Case Handling: Gracefully handle queries for states or cities that do not exist in the collection by returning informative messages rather than throwing null pointer exceptions.



>>>>>>> f98feec4012c54facf8e17d6f0f2632bc02e8137
Non-Functional Requirements:
Lookup Efficiency: Retrieving a city's population when the state and city are known should execute in O(1) average time complexity.
Data Integrity: Prevent duplicate state names and duplicate city names within the same state.
