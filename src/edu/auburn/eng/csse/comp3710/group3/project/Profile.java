package edu.auburn.eng.csse.comp3710.group3.project;

import android.content.SharedPreferences;

public class Profile {

	private int age, gender, chol, hdl, bp, risk;
	private boolean isSmoker, isOnMeds;
	private String name;

	public static final String PREFS_NAME = "settings_preferences";

	public Profile() {

	}

	public Profile(String name, int age, int gender, int chol, int hdl, int bp,
			int risk, boolean isSmoker, boolean isOnMeds) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.chol = chol;
		this.hdl = hdl;
		this.bp = bp;
		this.risk = risk;
		this.isSmoker = isSmoker;
		this.isOnMeds = isOnMeds;
	}

	public Profile(SharedPreferences prefsIn) {
		this.name = prefsIn.getString("name", "");
		this.age = prefsIn.getInt("age", 0);
		this.gender = prefsIn.getInt("gender", FraminghamRiskScore.MALE);
		this.chol = prefsIn.getInt("chol", 0);
		this.hdl = prefsIn.getInt("hdl", 0);
		this.bp = prefsIn.getInt("bp", 0);
		this.risk = prefsIn.getInt("riskScore", 0);
		this.isSmoker = prefsIn.getBoolean("isSmoker", false);
		this.isOnMeds = prefsIn.getBoolean("isOnMeds", false);

	}

	// Getters and Setters

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * @return the chol
	 */
	public int getChol() {
		return chol;
	}

	/**
	 * @param chol
	 *            the chol to set
	 */
	public void setChol(int chol) {
		this.chol = chol;
	}

	/**
	 * @return the hdl
	 */
	public int getHdl() {
		return hdl;
	}

	/**
	 * @param hdl
	 *            the hdl to set
	 */
	public void setHdl(int hdl) {
		this.hdl = hdl;
	}

	/**
	 * @return the bp
	 */
	public int getBp() {
		return bp;
	}

	/**
	 * @param bp
	 *            the bp to set
	 */
	public void setBp(int bp) {
		this.bp = bp;
	}

	/**
	 * @return the risk
	 */
	public int getRisk() {
		return risk;
	}

	/**
	 * @param risk
	 *            the risk to set
	 */
	public void setRisk(int risk) {
		this.risk = risk;
	}

	/**
	 * @return the isSmoker
	 */
	public boolean isSmoker() {
		return isSmoker;
	}

	/**
	 * @param isSmoker
	 *            the isSmoker to set
	 */
	public void setSmoker(boolean isSmoker) {
		this.isSmoker = isSmoker;
	}

	/**
	 * @return the isOnMeds
	 */
	public boolean isOnMeds() {
		return isOnMeds;
	}

	/**
	 * @param isOnMeds
	 *            the isOnMeds to set
	 */
	public void setOnMeds(boolean isOnMeds) {
		this.isOnMeds = isOnMeds;
	}

}
