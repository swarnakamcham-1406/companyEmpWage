package employee;



import java.util.ArrayList;
import java.util.HashMap;

class EmployeeWageOops implements InEmployeeWageOops {
//constants
public static final int IS_FULL_TIME=1;
public static final int IS_PART_TIME=2;
int daySalary;
//list
ArrayList<Integer> dailyWage = new ArrayList<Integer>();
ArrayList<CompanyEmpWage> CompanyEmpWageArrayList;
HashMap<String, CompanyEmpWage> CompanyEmpWageMap;

public EmployeeWageOops() {
  CompanyEmpWageArrayList = new ArrayList<>();
  CompanyEmpWageMap = new HashMap<>();
}

public void dailyWage() {
  dailyWage.add(daySalary);
}

public void addCompanyEmpWage(String company, int empRatePerHr, int numberOfWorkingDays, int maxHrPerMonth) {
  CompanyEmpWage companyEmpWage = new CompanyEmpWage(company, empRatePerHr, numberOfWorkingDays, maxHrPerMonth);
  CompanyEmpWageArrayList.add(companyEmpWage);
  CompanyEmpWageMap.put(company, companyEmpWage);
}

public void computeEmpWage() {
  for(int i=0;i<CompanyEmpWageArrayList.size();i++) {
    CompanyEmpWage companyEmpWage = CompanyEmpWageArrayList.get(i);
    companyEmpWage.setTotalEmpWage(this.computeEmpWage(companyEmpWage));
  }
}

public int computeEmpWage(CompanyEmpWage companyEmpWage) {
  int empHrs = 0;
  int totalEmpHrs = 0;
  int totalWorkingDays = 0;

  while(totalEmpHrs <= companyEmpWage.maxHrPerMonth && totalWorkingDays < companyEmpWage.numberOfWorkingDays) {
    totalWorkingDays++;
    double empCheck = Math.floor(Math.random() * 10) % 3;
    switch ((int) empCheck) {
      case IS_FULL_TIME:
        empHrs = 8;
        break;
      case IS_PART_TIME:
        empHrs = 4;
        break;
      default:
        empHrs = 0;
    }
    int daySalary = empHrs * companyEmpWage.empRatePerHr;
    companyEmpWage.dailyWage.add(daySalary);
    totalEmpHrs += empHrs;
    System.out.println("totalWorkingDays: " + totalWorkingDays + "/ daily empHrs: " + empHrs + "/ totalEmpHrs: " + totalEmpHrs);
  }
  System.out.println("\nTotal emp wage : " +  (totalEmpHrs * companyEmpWage.empRatePerHr) + "\n");
  return totalEmpHrs * companyEmpWage.empRatePerHr;
}

public int getTotalEmpWage(String company) {
  return CompanyEmpWageMap.get(company).totalEmpWage;
}

public static void main(String[] args) {
  EmployeeWageOops empWageBuilder = new EmployeeWageOops();
  empWageBuilder.addCompanyEmpWage("sbi", 50, 20, 100);
  empWageBuilder.addCompanyEmpWage("sc", 40, 20, 100);
  empWageBuilder.addCompanyEmpWage("boi", 30, 20, 100);
  empWageBuilder.addCompanyEmpWage("uco", 30, 20, 100);
  empWageBuilder.addCompanyEmpWage("sbi", 50, 20, 100);
  empWageBuilder.computeEmpWage();
  System.out.println("Total wage of the searched company: " + empWageBuilder.getTotalEmpWage("sbi"));
} //main
}// class

//interface
interface InEmployeeWageOops {
  public void addCompanyEmpWage(String company, int empRatePerHr, int numberOfWorkingDays, int maxHrPerMonth);
}

//POJO class or object
class CompanyEmpWage {
  //attributes
  public final String company;
  public final int empRatePerHr;
  public final int numberOfWorkingDays;
  public final int maxHrPerMonth;
  public int totalEmpWage;
  ArrayList<Integer> dailyWage = new ArrayList<Integer>();

  //constructor
  public CompanyEmpWage(String company, int empRatePerHr, int numberOfWorkingDays, int maxHrPerMonth) {
    this.company = company;
    this.empRatePerHr = empRatePerHr;
    this.numberOfWorkingDays = numberOfWorkingDays;
    this.maxHrPerMonth = maxHrPerMonth;
  }

  //setter
  public void setTotalEmpWage(int totalEmpWage) {
    this.totalEmpWage = totalEmpWage;
  }

  public String toString() {
    return "Total employee wages for: " + company + " is " + totalEmpWage;
  }

}