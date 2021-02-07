package com.bitssmart.smartRestaurant.Controller;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("paytm.payment.sandbox")
public class PaytmDetailPojo {

	private String merchantId;

	private String merchantKey;

	private String channelId;

	private String website;

	private String industryTypeId;

	private String paytmUrl;

	private Map<String, String> details;

	public PaytmDetailPojo() {
	}

	public PaytmDetailPojo(String merchantId, String merchantKey, String channelId, String website,
			String industryTypeId, String paytmUrl, Map<String, String> details) {
		super();
		this.merchantId = merchantId;
		this.merchantKey = merchantKey;
		this.channelId = channelId;
		this.website = website;
		this.industryTypeId = industryTypeId;
		this.paytmUrl = paytmUrl;
		this.details = details;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantKey() {
		return merchantKey;
	}

	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getIndustryTypeId() {
		return industryTypeId;
	}

	public void setIndustryTypeId(String industryTypeId) {
		this.industryTypeId = industryTypeId;
	}

	public String getPaytmUrl() {
		return paytmUrl;
	}

	public void setPaytmUrl(String paytmUrl) {
		this.paytmUrl = paytmUrl;
	}

	public Map<String, String> getDetails() {
		return details;
	}

	public void setDetails(Map<String, String> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "PaytmDetailPojo [merchantId=" + merchantId + ", merchantKey=" + merchantKey + ", channelId=" + channelId
				+ ", website=" + website + ", industryTypeId=" + industryTypeId + ", paytmUrl=" + paytmUrl
				+ ", details=" + details + "]";
	}

}