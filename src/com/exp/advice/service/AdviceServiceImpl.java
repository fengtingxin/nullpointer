package com.exp.advice.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.advice.dao.AdviceDaoImpl;
import com.exp.entity.Advice;

@Service
@Transactional(readOnly = true)
public class AdviceServiceImpl {

	@Resource
	private AdviceDaoImpl adviceDaoImpl;
	/**
	 * 功能：
	 * 保存用户提交的建议
	 * @param advice
	 * @return
	 * @author fengtingxin
	 */
	@Transactional(readOnly = false)
	public void saveAdvice(Advice advice){
		try {
			this.adviceDaoImpl.save(advice);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//处理异常！
			saveAdvice(advice);
		}
	}
	/**
	 * 功能：
	 * 查找到所有的建议
	 * @return
	 * List<Advice>
	 * @author fengtingxin
	 */
	public List<Advice> findAllAdvice(){
		return this.adviceDaoImpl.fingAllAdvice();
	}
	/**
	 * 功能：
	 * 删除一个建议
	 * 通过id
	 * @param id
	 * @author fengtingxin
	 */
	@Transactional(readOnly = false)
	public void deleteOneAdvice(Integer id){
		try {
			this.adviceDaoImpl.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("advice exception about delete one!");
			try {
				Advice advice =this.adviceDaoImpl.get(id);
				this.adviceDaoImpl.delete(advice);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	/**
	 * 功能：
	 * 通过id找到一个advice
	 * @param adviceId
	 * @return
	 * @author fengtingxin
	 */
	public Advice fingOneAdvice(Integer adviceId){
		try {
			return this.adviceDaoImpl.get(adviceId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 功能：
	 * 更新advice是否查看
	 * @param advice
	 * @author fengtingxin
	 */
	@Transactional(readOnly = false)
	public void updateAdvice(Advice advice){
		try {
			this.adviceDaoImpl.update(advice);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("update advice error");
			try {
				this.adviceDaoImpl.excuteBySql("update advice set visable=? where adviceId=? ", new Object[]{advice.getAdviceId(),advice.isVisable()});
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("update advice error by sql");
				e1.printStackTrace();
			}
		}
	}
}
