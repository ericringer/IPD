//
//  NameSearchResult.h
//  SFIndieFest
//
//  Created by eric ringer on 10/6/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Data.h"
@interface NameSearchResult : NSObject

@property (nonatomic, retain)NSString *status;
@property (nonatomic, retain)NSString *dataMessage;
@property (nonatomic, retain)Data *data;
- (id)init;
- (id)initWithResponse:(NSData *)response;
@end
