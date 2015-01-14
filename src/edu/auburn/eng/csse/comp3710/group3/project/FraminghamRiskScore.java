package edu.auburn.eng.csse.comp3710.group3.project;

public class FraminghamRiskScore {

	public static final int MALE = 1, FEMALE = 0;

	public FraminghamRiskScore() {

	}

	// Returns an int that should be interpreted as a percent.
	public static int calculateRiskAsInt(int ageIn, int genderIn,
			int totalCholIn, int HDLCholIn, int BPIn, boolean isBP_TreatedIn,
			boolean isSmokerIn) {

		int totalRiskSum = 0;
		// If risk = 0, this should be interpreted as "<1%"
		int risk = 0;

		totalRiskSum += calculateAge(ageIn, genderIn);
		totalRiskSum += calculateTotalChol(ageIn, genderIn, totalCholIn);
		totalRiskSum += calculateSmoker(ageIn, genderIn, isSmokerIn);
		totalRiskSum += calculateHDLChol(HDLCholIn);
		totalRiskSum += calculateBP(genderIn, BPIn, isBP_TreatedIn);

		risk = findRisk(genderIn, totalRiskSum);

		return risk;
	}

	private static int calculateAge(int ageIn, int genderIn) {

		int riskSum = 0;

		if (genderIn == MALE) {
			if (ageIn >= 20 && ageIn <= 34) {
				riskSum = -9;
			}

			else if (ageIn >= 35 && ageIn <= 39) {
				riskSum = -4;
			}

			else if (ageIn >= 40 && ageIn <= 44) {
				riskSum = 0;
			}

			else if (ageIn >= 45 && ageIn <= 49) {
				riskSum = 3;
			}

			else if (ageIn >= 50 && ageIn <= 54) {
				riskSum = 6;
			}

			else if (ageIn >= 55 && ageIn <= 59) {
				riskSum = 8;
			}

			else if (ageIn >= 60 && ageIn <= 64) {
				riskSum = 10;
			}

			else if (ageIn >= 65 && ageIn <= 69) {
				riskSum = 11;
			}

			else if (ageIn >= 70 && ageIn <= 74) {
				riskSum = 12;
			}

			else if (ageIn >= 75 && ageIn <= 79) {
				riskSum = 13;
			}
		}

		else if (genderIn == FEMALE) {
			if (ageIn >= 20 && ageIn <= 34) {
				riskSum = -7;
			}

			else if (ageIn >= 35 && ageIn <= 39) {
				riskSum = -3;
			}

			else if (ageIn >= 40 && ageIn <= 44) {
				riskSum = 0;
			}

			else if (ageIn >= 45 && ageIn <= 49) {
				riskSum = 3;
			}

			else if (ageIn >= 50 && ageIn <= 54) {
				riskSum = 6;
			}

			else if (ageIn >= 55 && ageIn <= 59) {
				riskSum = 8;
			}

			else if (ageIn >= 60 && ageIn <= 64) {
				riskSum = 10;
			}

			else if (ageIn >= 65 && ageIn <= 69) {
				riskSum = 12;
			}

			else if (ageIn >= 70 && ageIn <= 74) {
				riskSum = 14;
			}

			else if (ageIn >= 75 && ageIn <= 79) {
				riskSum = 16;
			}
		}

		else {
			// Should Never Happen
		}

		return riskSum;
	}

	private static int calculateTotalChol(int ageIn, int genderIn,
			int totalCholIn) {

		int riskSum = -1;

		if (genderIn == MALE) {
			if (ageIn >= 20 && ageIn <= 39) {
				if (totalCholIn < 160) {
					riskSum = 0;
				}

				else if (totalCholIn >= 160 && totalCholIn <= 199) {
					riskSum = 4;
				}

				else if (totalCholIn >= 200 && totalCholIn <= 239) {
					riskSum = 7;
				}

				else if (totalCholIn >= 240 && totalCholIn <= 279) {
					riskSum = 9;
				}

				else if (totalCholIn >= 280) {
					riskSum = 11;
				}

				else {
					// Should Never Happen
				}
			}

			else if (ageIn >= 40 && ageIn <= 49) {
				if (totalCholIn < 160) {
					riskSum = 0;
				}

				else if (totalCholIn >= 160 && totalCholIn <= 199) {
					riskSum = 3;
				}

				else if (totalCholIn >= 200 && totalCholIn <= 239) {
					riskSum = 5;
				}

				else if (totalCholIn >= 240 && totalCholIn <= 279) {
					riskSum = 6;
				}

				else if (totalCholIn >= 280) {
					riskSum = 8;
				}

				else {
					// Should Never Happen
				}
			}

			else if (ageIn >= 50 && ageIn <= 59) {
				if (totalCholIn < 160) {
					riskSum = 0;
				}

				else if (totalCholIn >= 160 && totalCholIn <= 199) {
					riskSum = 2;
				}

				else if (totalCholIn >= 200 && totalCholIn <= 239) {
					riskSum = 3;
				}

				else if (totalCholIn >= 240 && totalCholIn <= 279) {
					riskSum = 4;
				}

				else if (totalCholIn >= 280) {
					riskSum = 5;
				}

				else {
					// Should Never Happen
				}
			}

			else if (ageIn >= 60 && ageIn <= 69) {
				if (totalCholIn < 160) {
					riskSum = 0;
				}

				else if (totalCholIn >= 160 && totalCholIn <= 239) {
					riskSum = 1;
				}

				else if (totalCholIn >= 240 && totalCholIn <= 279) {
					riskSum = 2;
				}

				else if (totalCholIn >= 280) {
					riskSum = 3;
				}

				else {
					// Should Never Happen
				}
			}

			else if (ageIn >= 70 && ageIn <= 79) {
				if (totalCholIn <= 239) {
					riskSum = 0;
				}

				else if (totalCholIn >= 240) {
					riskSum = 1;
				}

				else {
					// Should Never Happen
				}
			}

			else {
				// Should Never Happen - Age was Under 20 or over 79
			}
		}

		if (genderIn == FEMALE) {
			if (ageIn >= 20 && ageIn <= 39) {
				if (totalCholIn < 160) {
					riskSum = 0;
				}

				else if (totalCholIn >= 160 && totalCholIn <= 199) {
					riskSum = 4;
				}

				else if (totalCholIn >= 200 && totalCholIn <= 239) {
					riskSum = 8;
				}

				else if (totalCholIn >= 240 && totalCholIn <= 279) {
					riskSum = 11;
				}

				else if (totalCholIn >= 280) {
					riskSum = 13;
				}

				else {
					// Should Never Happen
				}
			}

			else if (ageIn >= 40 && ageIn <= 49) {
				if (totalCholIn < 160) {
					riskSum = 0;
				}

				else if (totalCholIn >= 160 && totalCholIn <= 199) {
					riskSum = 3;
				}

				else if (totalCholIn >= 200 && totalCholIn <= 239) {
					riskSum = 6;
				}

				else if (totalCholIn >= 240 && totalCholIn <= 279) {
					riskSum = 8;
				}

				else if (totalCholIn >= 280) {
					riskSum = 10;
				}

				else {
					// Should Never Happen
				}
			}

			else if (ageIn >= 50 && ageIn <= 59) {
				if (totalCholIn < 160) {
					riskSum = 0;
				}

				else if (totalCholIn >= 160 && totalCholIn <= 199) {
					riskSum = 2;
				}

				else if (totalCholIn >= 200 && totalCholIn <= 239) {
					riskSum = 4;
				}

				else if (totalCholIn >= 240 && totalCholIn <= 279) {
					riskSum = 5;
				}

				else if (totalCholIn >= 280) {
					riskSum = 7;
				}

				else {
					// Should Never Happen
				}
			}

			else if (ageIn >= 60 && ageIn <= 69) {
				if (totalCholIn < 160) {
					riskSum = 0;
				}

				else if (totalCholIn >= 160 && totalCholIn <= 199) {
					riskSum = 1;
				}

				else if (totalCholIn >= 200 && totalCholIn <= 239) {
					riskSum = 2;
				}

				else if (totalCholIn >= 240 && totalCholIn <= 279) {
					riskSum = 3;
				}

				else if (totalCholIn >= 280) {
					riskSum = 4;
				}

				else {
					// Should Never Happen
				}
			}

			else if (ageIn >= 70 && ageIn <= 79) {
				if (totalCholIn < 160) {
					riskSum = 0;
				}

				else if (totalCholIn >= 160 && totalCholIn <= 239) {
					riskSum = 1;
				}

				else if (totalCholIn >= 240) {
					riskSum = 2;
				}

				else {
					// Should Never Happen
				}
			}
		}

		return riskSum;
	}

	private static int calculateSmoker(int ageIn, int genderIn,
			boolean isSmokerIn) {

		int riskSum = 0;

		if (isSmokerIn) {
			if (genderIn == MALE) {
				if (ageIn >= 20 && ageIn <= 39) {
					riskSum = 8;
				}

				else if (ageIn >= 40 && ageIn <= 49) {
					riskSum = 5;
				}

				else if (ageIn >= 50 && ageIn <= 59) {
					riskSum = 3;
				}

				else if (ageIn >= 60 && ageIn <= 79) {
					riskSum = 1;
				}

				else {
					// Should Never Happen
				}
			}

			else if (genderIn == FEMALE) {
				if (ageIn >= 20 && ageIn <= 39) {
					riskSum = 9;
				}

				else if (ageIn >= 40 && ageIn <= 49) {
					riskSum = 7;
				}

				else if (ageIn >= 50 && ageIn <= 59) {
					riskSum = 4;
				}

				else if (ageIn >= 60 && ageIn <= 69) {
					riskSum = 2;
				}

				else if (ageIn >= 70 && ageIn <= 79) {
					riskSum = 1;
				}

				else {
					// Should Never Happen
				}
			}

		}

		return riskSum;
	}

	private static int calculateHDLChol(int HDLCholIn) {

		int riskSum = 0;

		if (HDLCholIn >= 60) {
			riskSum = -1;
		}

		else if (HDLCholIn >= 50 && HDLCholIn <= 59) {
			riskSum = 0;
		}

		else if (HDLCholIn >= 40 && HDLCholIn <= 49) {
			riskSum = 1;
		}

		else if (HDLCholIn < 40) {
			riskSum = 2;
		}

		else {
			// Should Never Happen
		}

		return riskSum;
	}

	private static int calculateBP(int genderIn, int BPIn,
			boolean isBP_TreatedIn) {

		int riskSum = 0;

		if (genderIn == MALE) {
			if (!isBP_TreatedIn) {
				if (BPIn < 129) {
					riskSum = 0;
				}

				else if (BPIn >= 130 && BPIn <= 159) {
					riskSum = 1;
				}

				else if (BPIn > 160) {
					riskSum = 2;
				}

				else {
					// Should Never Happen
				}
			}

			if (isBP_TreatedIn) {
				if (BPIn < 120) {
					riskSum = 0;
				}

				else if (BPIn >= 120 && BPIn <= 129) {
					riskSum = 1;
				}
				
				else if (BPIn >= 130 && BPIn <= 159) {
					riskSum = 2;
				}

				else if (BPIn > 160) {
					riskSum = 3;
				}

				else {
					// Should Never Happen
				}
			}
		}

		else if (genderIn == FEMALE) {
			if (!isBP_TreatedIn) {
				if (BPIn < 120) {
					riskSum = 0;
				}

				else if (BPIn >= 120 && BPIn <= 129) {
					riskSum = 1;
				}

				else if (BPIn >= 130 && BPIn <= 139) {
					riskSum = 2;
				}

				else if (BPIn >= 140 && BPIn <= 159) {
					riskSum = 3;
				}

				else if (BPIn > 160) {
					riskSum = 4;
				}

				else {
					// Should Never Happen
				}
			}

			if (isBP_TreatedIn) {
				if (BPIn < 120) {
					riskSum = 0;
				}

				else if (BPIn >= 120 && BPIn <= 129) {
					riskSum = 3;
				}

				else if (BPIn >= 130 && BPIn <= 139) {
					riskSum = 4;
				}

				else if (BPIn >= 140 && BPIn <= 159) {
					riskSum = 5;
				}

				else if (BPIn > 160) {
					riskSum = 6;
				}

				else {
					// Should Never Happen
				}
			}
		}

		return riskSum;
	}

	private static int findRisk(int genderIn, int riskSumIn) {

		int risk = 0;

		if (genderIn == MALE) {
			if (riskSumIn >= 17) {
				// This should be interpreted as "30%+"
				risk = 30;
			}

			// If risk <= 0, this should be interpreted as "<1%"
			else if (riskSumIn <= 0) {
				risk = 0;
			}

			else {
				switch (riskSumIn) {
				case 1:
					risk = 1;
					break;

				case 2:
					risk = 1;
					break;

				case 3:
					risk = 1;
					break;

				case 4:
					risk = 1;
					break;

				case 5:
					risk = 2;
					break;

				case 6:
					risk = 2;
					break;

				case 7:
					risk = 3;
					break;

				case 8:
					risk = 4;
					break;

				case 9:
					risk = 5;
					break;

				case 10:
					risk = 6;
					break;

				case 11:
					risk = 8;
					break;

				case 12:
					risk = 10;
					break;

				case 13:
					risk = 12;
					break;

				case 14:
					risk = 16;
					break;

				case 15:
					risk = 20;
					break;

				case 16:
					risk = 25;
					break;

				// Should never happen
				default:
					risk = -1;
					break;

				}
				;
			}
		}

		else if (genderIn == FEMALE) {
			if (riskSumIn >= 25) {
				// This should be interpreted as "30%+"
				risk = 30;
			}

			// If risk <= 0, this should be interpreted as "<1%"
			else if (riskSumIn < 9) {
				risk = 0;
			}

			else {
				switch (riskSumIn) {
				case 9:
					risk = 1;
					break;

				case 10:
					risk = 1;
					break;

				case 11:
					risk = 1;
					break;

				case 12:
					risk = 1;
					break;

				case 13:
					risk = 2;
					break;

				case 14:
					risk = 2;
					break;

				case 15:
					risk = 3;
					break;

				case 16:
					risk = 4;
					break;

				case 17:
					risk = 5;
					break;

				case 18:
					risk = 6;
					break;

				case 19:
					risk = 8;
					break;

				case 20:
					risk = 11;
					break;

				case 21:
					risk = 14;
					break;

				case 22:
					risk = 17;
					break;

				case 23:
					risk = 22;
					break;

				case 24:
					risk = 27;
					break;

				// Should never happen
				default:
					risk = -1;
					break;

				}
				;
			}
		}
		return risk;
	}

	public String toString() {

		return null;

	}

}
