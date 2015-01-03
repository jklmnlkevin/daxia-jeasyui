package com.daxia.wy.dto;

import com.daxia.wy.model.PayRecord;

/**
 * DTO是数据传输对象的缩写，有的时候我们需要用到的属性比model的要多，但由于model的属性是与
 * 数据库表一一对应，所以，我们不能在model里增加属性。还有的时候，比如页面需要的是date的字符串形式，
 * 但model里的date是日期格式，在页面不好显示，这时候可以转成字符串形式放到dto对象里，页面取就很方便了。
 * 因此，与页面交互，即在Controller控制层里，请使用DTO对象，同理，service返回给controller的对象也应该是
 * dto对象，而不是model对应。这样还有个好处，比如service的update方法，如果是controller传个model对象给service,
 * 必须先把model从数据库load出来，不然，service层需要load，造成职责不成。（如果不先load出对象就更新，会有异常，
 * 因为model对象并不在hibernate的session 托管状态）。
 * 
 * 由于DTO对象属性大多都是与model一致，所以，每个dto都继承自对应的model对应，有与model不同的，再单独写在dto里。
 * 
 * DTO还可以用在dao层的查询方法。
 * 
 * @author Kewen.Zhu
 *
 */
public class PayRecordDTO extends PayRecord {
	
}
