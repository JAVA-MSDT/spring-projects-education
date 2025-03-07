package com.javamsdt.clientdeal.domain;

import java.time.LocalDateTime;

public class ClientDeal {

	private long clientDealId;
	private long clientId;
	private String clientName;
	private long dealId;
	private String dealName;
	private LocalDateTime date;
	private int accepted;
	private int refused;

	public ClientDeal() {
	}

	public ClientDeal(final long clientId, final String clientName, final long dealId, final String dealName, final LocalDateTime date, final int accepted,
			final int refused) {
		this.clientId = clientId;
		this.clientName = clientName;
		this.dealId = dealId;
		this.dealName = dealName;
		this.date = date;
		this.accepted = accepted;
		this.refused = refused;
	}

	public ClientDeal(final long clientDealId, final long clientId, final String clientName, final long dealId, final String dealName,
			final LocalDateTime date, final int accepted, final int refused) {
		this.clientDealId = clientDealId;
		this.clientId = clientId;
		this.clientName = clientName;
		this.dealId = dealId;
		this.dealName = dealName;
		this.date = date;
		this.accepted = accepted;
		this.refused = refused;
	}

	public long getClientDealId() {
		return clientDealId;
	}

	public void setClientDealId(final long clientDealId) {
		this.clientDealId = clientDealId;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(final long clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(final String clientName) {
		this.clientName = clientName;
	}

	public long getDealId() {
		return dealId;
	}

	public void setDealId(final long dealId) {
		this.dealId = dealId;
	}

	public String getDealName() {
		return dealName;
	}

	public void setDealName(final String dealName) {
		this.dealName = dealName;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(final LocalDateTime date) {
		this.date = date;
	}

	public int getAccepted() {
		return accepted;
	}

	public void setAccepted(final int accepted) {
		this.accepted = accepted;
	}

	public int getRefused() {
		return refused;
	}

	public void setRefused(final int refused) {
		this.refused = refused;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientDeal [clientDealId=");
		builder.append(clientDealId);
		builder.append(", clientId=");
		builder.append(clientId);
		builder.append(", clientName=");
		builder.append(clientName);
		builder.append(", dealId=");
		builder.append(dealId);
		builder.append(", dealName=");
		builder.append(dealName);
		builder.append(", date=");
		builder.append(date);
		builder.append(", accepted=");
		builder.append(accepted);
		builder.append(", refused=");
		builder.append(refused);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + accepted;
		result = (prime * result) + (int) (clientDealId ^ (clientDealId >>> 32));
		result = (prime * result) + (int) (clientId ^ (clientId >>> 32));
		result = (prime * result) + ((clientName == null) ? 0 : clientName.hashCode());
		result = (prime * result) + ((date == null) ? 0 : date.hashCode());
		result = (prime * result) + (int) (dealId ^ (dealId >>> 32));
		result = (prime * result) + ((dealName == null) ? 0 : dealName.hashCode());
		result = (prime * result) + refused;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ClientDeal other = (ClientDeal) obj;
		if (accepted != other.accepted) {
			return false;
		}
		if (clientDealId != other.clientDealId) {
			return false;
		}
		if (clientId != other.clientId) {
			return false;
		}
		if (clientName == null) {
			if (other.clientName != null) {
				return false;
			}
		} else if (!clientName.equals(other.clientName)) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (dealId != other.dealId) {
			return false;
		}
		if (dealName == null) {
			if (other.dealName != null) {
				return false;
			}
		} else if (!dealName.equals(other.dealName)) {
			return false;
		}
		if (refused != other.refused) {
			return false;
		}
		return true;
	}

}
