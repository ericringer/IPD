//
//  KnownFor.h
//  SFIndieFest
//
//  Created by eric ringer on 10/6/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface KnownFor : NSObject
@property (nonatomic, retain)NSString *title;
@property (nonatomic, retain)NSString *url;

- (id)initWithJSON:(NSDictionary *)json;
@end
