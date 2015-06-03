package nju.iip.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nju.iip.dto.Options;
import nju.iip.dto.Questions;
import nju.iip.dto.Scale;
import nju.iip.util.DBConnection;

/**
 * 与量表有关的数据库操作
 * @author wangqiang
 * @since 2015-6-2
 */
public class ScaleDaoImpl {
	
	 static Connection conn  = null;
	 static Statement sm = null;
	 static ResultSet rs = null;
	 static PreparedStatement ps = null;
	
	
	/**
	 * 根据totalScaleId 从parkinsonscale表中取出所有题目的questionId
	 * @param totalScaleId
	 * @return
	 */
	public static List<Integer> getQuestionId(int totalScaleId) {
		List<Integer> questionId_list = new ArrayList<Integer>();
		conn = DBConnection.getConn();
		String sql = "select * from parkinsonscale where totalScaleId='"+totalScaleId+"'";
		try {
			sm=conn.createStatement();
			rs=sm.executeQuery(sql);
			while(rs.next()) {
				int questionId = rs.getInt("questionId");//取出questionId
				questionId_list.add(questionId);
			}
		}catch(Exception e){
			e.printStackTrace();
			}
		finally {
			closeDB();
		}
		return questionId_list;
	}
	
	
	/**
	 * 根据questionId_list取出所有对应题目和选项
	 * @param questionId_list
	 * @return List<Questions> question_list
	 */
	public static List<Questions> getQuestions(List<Integer> questionId_list) {
		List<Questions> question_list = new ArrayList<Questions>();
		conn = DBConnection.getConn();
		try {
			for(Integer questionId:questionId_list) {
				Questions question = new Questions();
				String sql = "select * from scalequestion where id='"+questionId+"'";
				sm=conn.createStatement();
				rs=sm.executeQuery(sql);
				//取出题目的内容
				if(rs.next()) {
					question.setQuestionContent(rs.getString("questionContent"));
					question.setShowType(rs.getString("showType"));
				}
				List<Options> options = new ArrayList<Options>();
				sql = "select * from questionoption where questionId='"+questionId+"'";
				sm=conn.createStatement();
				rs=sm.executeQuery(sql);
				//取出题目对应的选项
				while(rs.next()) {
					Options option = new Options();
					option.setOptionContent(rs.getString("optionContent"));
					option.setOptionValue(rs.getString("optionValue"));
					options.add(option);
				}
				question.setAnswers(options);
				question.setQuestionId(questionId);
				question_list.add(question);
			}
		}catch(Exception e){
			e.printStackTrace();
			}
		finally {
			closeDB();
		}
		return question_list;
	}
	
	/**
	 * 根据totalScaleId 从parkinsontotalscale表中取出对应量表的信息
	 * @param totalScaleId
	 * @return scale
	 */
	public static Scale getScale(int totalScaleId) {
		Scale scale = new Scale();
		conn = DBConnection.getConn();
		String sql = "select * from parkinsontotalscale where id='"+totalScaleId+"'";
		try {
			sm=conn.createStatement();
			rs=sm.executeQuery(sql);
			if(rs.next()) {
				scale.setScaleName(rs.getString("scaleName"));
				scale.setScaleDescription(rs.getString("scaleDescription"));
				scale.setShortname(rs.getString("shortname"));
				scale.setId(totalScaleId+"");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			closeDB();
		}
		return scale;
	}
	
	
	
	/**
	 * 关闭数据库
	 */
	public static void closeDB() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (sm != null) {
			try {
				sm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(getScale(125).getScaleName());
	}

}
