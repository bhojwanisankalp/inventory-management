package com.chatbot.dao;

import java.util.List;

import com.chatbot.model.Agents;
import com.chatbot.model.User;


/*
 * DAO INTERFACE FOR SER TYPE AGENT 
 * */
public interface AgentDao {
	/*
	 * To add new Agent
	 * @param Agents instance with agent details
	 * */
	public void addAgent(Agents p);
	
	/*
	 * To update the agents details
	 * @param Agents instance with updated agents details
	 * */
	public void updateAgent(Agents p);
	
	/*
	 * To get the List of all agents.
	 * @return List of type Agents.
	 * */
	public List<Agents> listAgents();
	
	/*
	 * To get a single User instance associated with the id provided
	 * @param Integer value of agents-id
	 * @return Agents for
	 * */
	public Agents getAgentById(int id);
	
	/*
	 * To delete the User instance associated with the id provided.
	 * @param Integer value of agents-id.
	 * */
	public void removeAgent(int id);
	
	/*
	 * To check if the email is already associated with an existing agent.
	 * @param String value of agents-email.
	 * */
	public boolean isEmailExist(String email);
	
	/*
	 * To check if the email is already associated with an existing agent.
	 * @param String value of agents-email.
	 * */
	 public boolean isContactExist(String contact);
	 
	 /*
	  * To count the total number of rows for agents entity.
	  * @return Long value of total row count
	  * */
	 public long totalPageCount();
	 
	 /*
	  * To get list of agents.
	  * @param Integer value of start index for table agents and limit to extract fixed number of rows.
	  * @return List of type Agents
	  * */
	 public List<Agents> listPerPage(int start,int limit);
}
