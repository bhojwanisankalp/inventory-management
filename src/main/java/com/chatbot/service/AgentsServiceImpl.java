package com.chatbot.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.chatbot.dao.AgentDao;
import com.chatbot.model.Agents;

public class AgentsServiceImpl implements AgentsService {

	private AgentDao agentDoa;

	public void setAgentDoa(AgentDao agentDoa) {
		this.agentDoa = agentDoa;
	}

	@Override
	@Transactional
	public void addAgent(Agents p) {
		this.agentDoa.addAgent(p);	
	}

	@Override
	@Transactional
	public void updateAgent(Agents p) {
		this.agentDoa.updateAgent(p);
	}

	@Override
	public List<Agents> listAgents() {
		// TODO 
		return null;
	}

	@Override
	@Transactional
	public Agents getAgentById(int id) {
		return this.agentDoa.getAgentById(id);
	}

	@Override
	@Transactional
	public void removeAgent(int id) {
		this.agentDoa.removeAgent(id); 
	}

	@Override
	@Transactional
	public boolean isEmailExist(String email) {
		return this.agentDoa.isEmailExist(email);
	}

	@Override
	@Transactional
	public boolean isContactExist(String contact) {
		return this.agentDoa.isContactExist(contact);
	}

	@Override
	@Transactional
	public long totalPageCount() {
		return this.agentDoa.totalPageCount();
	}

	@Override
	@Transactional
	public List<Agents> listPerPage(int start, int limit) {
		return this.agentDoa.listPerPage(start, limit);
	}

	
}
